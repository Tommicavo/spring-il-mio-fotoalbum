<script>
  import axios from "axios";

  export default {
    name: "CategoryPage",
    data() {
      return {
        categories: [],
        newCat: "",
      };
    },
    components: {},
    props: {},
    computed: {},
    methods: {
      async fetchCategories() {
        const endpoint = "http://127.0.0.1:8080/categories/api";
        try {
          const res = await axios.get(endpoint);
          this.categories = res.data;
        } catch (err) {
          console.error("Catch Error: ", err);
        }
      },
      async storeCategory() {
        const endpoint = "http://127.0.0.1:8080/categories/api";
        try {
          const res = await axios.post(endpoint, this.newCat);
          console.log(res.data);
          this.$router.go();
        } catch (err) {
          console.error("Catch Error: ", err);
        }
      },
      async deleteCategory(id) {
        const endpoint = `http://127.0.0.1:8080/categories/api/${id}`;
        try {
          const res = await axios.delete(endpoint);
          console.log(res.data);
          this.$router.go();
        } catch (err) {
          console.error("Catch Error: ", err);
        }
      },
    },
    mounted() {
      this.fetchCategories();
    },
  };
</script>

<template>
  <table class="table table-dark table-striped">
    <thead>
      <tr>
        <th scope="col">#</th>
        <th scope="col">Label</th>
        <th scope="col">Delete</th>
      </tr>
    </thead>
    <tbody>
      <tr v-for="category in categories" :key="category.id">
        <td>{{ category.id }}</td>
        <td>{{ category.label }}</td>
        <td>
          <button
            type="button"
            class="btn btn-danger"
            @click="deleteCategory(category.id)"
          >
            Remove
          </button>
        </td>
      </tr>
      <tr>
        <td class="text-center" colspan="3">
          <form method="POST" class="catForm" @submit.prevent="storeCategory">
            <div class="input-group">
              <input
                type="text"
                class="form-control"
                placeholder="Write new Category here"
                v-model.trim="newCat"
              />
              <button type="submit" class="btn btn-primary">Add</button>
            </div>
          </form>
        </td>
      </tr>
    </tbody>
  </table>
</template>

<style lang="scss" scoped>
  .catForm {
    width: 50%;
    margin: 0 auto;
  }
</style>
