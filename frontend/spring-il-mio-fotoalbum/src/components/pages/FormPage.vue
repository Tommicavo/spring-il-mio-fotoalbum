<script>
  import axios from "axios";

  export default {
    name: "FormPage",
    data() {
      return {
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
        let endpoint = "http://127.0.0.1:8080/photos/api";
        if (this.mode == "edit") {
          endpoint = `http://127.0.0.1:8080/photos/api/${this.$route.params.id}`;
        }

        const selectedCategoriesIds = Object.keys(this.photo.categories)
          .filter((key) => this.photo.categories[key])
          .map((key) => parseInt(key, 10));

        const data = {
          title: this.photo.title,
          description: this.photo.description,
          url: this.photo.url,
          visible: this.photo.visible,
          categoryIds: selectedCategoriesIds,
        };

        try {
          let res;
          if (this.mode == "edit") {
            res = await axios.put(endpoint, data);
          } else if (this.mode == "create") {
            res = await axios.post(endpoint, data);
          }
          const photo = res.data;
          this.$router.push({ name: "DetailPage", params: { id: photo.id } });
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
          type="text"
          class="form-control"
          id="title"
          placeholder="Photo title..."
          v-model.trim="photo.title"
        />
      </div>
      <div class="description mb-3">
        <label for="description" class="form-label">Description</label>
        <textarea
          class="form-control"
          id="description"
          rows="3"
          v-model.trim="photo.description"
        ></textarea>
      </div>
      <div class="url mb-3">
        <label for="url" class="form-label">Image url</label>
        <input
          type="text"
          class="form-control"
          id="url"
          placeholder="Image url..."
          v-model.trim="photo.url"
        />
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
      <div class="categories">
        <div class="category" v-for="category in categories" :key="category.id">
          <input
            class="form-check form-check-inline"
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
        <button type="submit" class="btn btn-success">Create</button>
      </div>
    </form>
  </div>
</template>

<style lang="scss" scoped>
  .storePhoto {
    flex-basis: 50%;
  }
</style>
