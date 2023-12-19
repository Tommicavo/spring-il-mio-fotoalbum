<script>
  import axios from "axios";
  import { store } from "../../assets/data/store";

  export default {
    name: "FormPage",
    data() {
      return {
        store,
        photo: {
          title: "",
          description: "",
          url: "https://picsum.photos/450/300?random=1",
          visible: true,
          categories: {},
        },
        categories: [],
        mode: "",
        photoBackup: {},
        errors: [],
      };
    },
    components: {},
    computed: {
      getPageTitle() {
        if (this.mode == "create") return "Publish your Photo";
        else if (this.mode == "edit") return "Edit your Photo";
      },
    },
    methods: {
      setMode() {
        this.mode = this.$route.params.mode;
      },
      getFieldErrors(fieldName) {
        return this.errors
          .filter((error) => error.field === fieldName)
          .map((error) => error.message);
      },
      async fetchCategories() {
        const endpoint = `http://127.0.0.1:8080/categories/api`;
        try {
          const res = await axios.get(endpoint);
          this.categories = res.data;
        } catch (err) {
          console.error("Catch Error: ", err);
        }
      },
      async savePhoto() {
        let endpoint;
        let data;

        this.errors = [];

        const selectedCategoriesIds = Object.keys(this.photo.categories)
          .filter((key) => this.photo.categories[key])
          .map((key) => parseInt(key, 10));

        const statusCode = {
          validateStatus: function (status) {
            return status < 500;
          },
        };

        if (this.mode == "edit") {
          endpoint = `http://127.0.0.1:8080/photos/api/${this.$route.params.id}`;
          data = {
            title: this.photo.title,
            description: this.photo.description,
            url: this.photo.url,
            visible: this.photo.visible,
            categoryIds: selectedCategoriesIds,
            user_id: this.photoBackup.user_id,
          };
        } else if (this.mode == "create") {
          endpoint = "http://127.0.0.1:8080/photos/api";
          data = {
            title: this.photo.title,
            description: this.photo.description,
            url: this.photo.url,
            visible: this.photo.visible,
            categoryIds: selectedCategoriesIds,
            user_id: store.user.id,
          };
        }

        try {
          let res;
          if (this.mode == "edit") {
            res = await axios.put(endpoint, data, statusCode);
            if (res.status != 200) {
              this.errors = res.data.errors.map((error) => ({
                field: error.field,
                message: error.defaultMessage,
              }));
              console.log("Field Errors: ", this.errors);
            } else {
              const photo = res.data;
              this.$router.push({
                name: "DetailPage",
                params: { id: photo.id },
              });
            }
          } else if (this.mode == "create") {
            res = await axios.post(endpoint, data, statusCode);
            if (res.status != 200) {
              this.errors = res.data.map((error) => ({
                field: error.field,
                message: error.message,
              }));
              console.log("Field Errors: ", this.errors);
            } else {
              const photo = res.data;
              this.$router.push({
                name: "DetailPage",
                params: { id: photo.id },
              });
            }
          }
        } catch (err) {
          console.error("Catch Error: ", err);
        }
      },
      async fetchPhoto() {
        if (this.mode != "edit") return;
        const endpoint = `http://127.0.0.1:8080/photos/api/${this.$route.params.id}`;
        try {
          const res = await axios.get(endpoint);
          this.photoBackup = res.data;
          this.loadOldPhotoData();
        } catch (err) {
          console.error("Catch Error: ", err);
        }
      },
      loadOldPhotoData() {
        this.photo.title = this.photoBackup.title;
        this.photo.description = this.photoBackup.description;
        this.photo.url = this.photoBackup.url;
        this.photo.visible = this.photoBackup.visible;

        const presentCategories = [];
        this.photoBackup.categories.forEach((category) => {
          presentCategories.push(category.id);
        });

        presentCategories.forEach((categoryId) => {
          this.photo.categories[categoryId] = true;
        });
      },
    },
    mounted() {
      this.setMode();
      this.fetchCategories();
      this.fetchPhoto();
    },
  };
</script>

<template>
  <h1 class="text-center text-danger py-5">{{ getPageTitle }}</h1>
  <div class="d-flex justify-content-center align-items-center">
    <form @submit.prevent="savePhoto" class="savePhoto">
      <div class="title mb-3">
        <label for="title" class="form-label">Title</label>
        <input
          :class="{ 'is-invalid': getFieldErrors('title').length > 0 }"
          type="text"
          class="form-control"
          id="title"
          placeholder="Photo title..."
          v-model.trim="photo.title"
        />
        <div class="titleError">
          <ul>
            <li
              class="errorMsg"
              v-for="error in getFieldErrors('title')"
              :key="error"
            >
              {{ error }}
            </li>
          </ul>
        </div>
      </div>
      <div class="description mb-3">
        <label for="description" class="form-label">Description</label>
        <textarea
          :class="{ 'is-invalid': getFieldErrors('description').length > 0 }"
          class="form-control"
          id="description"
          rows="3"
          v-model.trim="photo.description"
        ></textarea>
        <div class="descriptionError">
          <ul>
            <li
              class="errorMsg"
              v-for="error in getFieldErrors('description')"
              :key="error"
            >
              {{ error }}
            </li>
          </ul>
        </div>
      </div>
      <div class="url mb-3">
        <label for="url" class="form-label">Image url</label>
        <input
          :class="{ 'is-invalid': getFieldErrors('url').length > 0 }"
          type="text"
          class="form-control"
          id="url"
          placeholder="Image url..."
          v-model.trim="photo.url"
        />
        <div class="urlError">
          <ul>
            <li
              class="errorMsg"
              v-for="error in getFieldErrors('url')"
              :key="error"
            >
              {{ error }}
            </li>
          </ul>
        </div>
      </div>
      <div class="visible form-check">
        <input
          class="form-check-input"
          type="checkbox"
          id="visible"
          checked
          v-model.trim="photo.visible"
        />
        <label class="form-check-label" for="visible"> Published </label>
      </div>
      <div class="categories d-flex gap-3 py-2">
        <div
          class="category d-flex gap-1"
          v-for="category in categories"
          :key="category.id"
        >
          <input
            class="form-check"
            type="checkbox"
            :id="category.label"
            v-model.trim="photo.categories[category.id]"
          />
          <label class="form-check-label" :for="category.label">
            {{ category.label }}
          </label>
        </div>
      </div>
      <div class="d-flex justify-content-evenly align-items-center my-5">
        <router-link class="btn btn-primary" :to="{ name: 'HomePage' }"
          >Back</router-link
        >
        <button v-if="mode == 'edit'" type="submit" class="btn btn-warning">
          Edit
        </button>
        <button v-if="mode == 'create'" type="submit" class="btn btn-success">
          Create
        </button>
      </div>
    </form>
  </div>
</template>

<style lang="scss" scoped>
  .storePhoto {
    flex-basis: 50%;
  }

  .errorMsg {
    font-size: 1rem;
    font-weight: 500;
    padding-left: 10px;
    color: #dc3545;
  }
</style>
