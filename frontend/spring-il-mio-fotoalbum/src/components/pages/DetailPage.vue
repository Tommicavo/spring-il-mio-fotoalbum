<script>
  import axios from "axios";

  export default {
    name: "DetailPage",
    data() {
      return {
        photo: {
          title: "",
          description: "",
          visible: false,
          categories: [],
        },
      };
    },
    components: {},
    props: {},
    computed: {},
    methods: {
      async fetchPhoto() {
        const endpoint = `http://127.0.0.1:8080/photos/api/${this.$route.params.id}`;
        try {
          const res = await axios.get(endpoint);
          const photo = res.data;
          Object.assign(this.photo, photo);
        } catch (err) {
          console.error("Catch Error: ", err);
        }
      },
      getCategories(photo) {
        let output = "";
        const categories = photo.categories;
        categories.forEach((category) => {
          output += "#" + category.label + " ";
        });
        return output;
      },
    },
    mounted() {
      this.fetchPhoto();
    },
  };
</script>

<template>
  <div class="detailPage d-flex justify-content-center align-items-center">
    <div class="myDetailedCard">
      <h3 class="text-center py-2">{{ photo.title }}</h3>
      <div class="text-center fst-italic p-2">"{{ photo.description }}"</div>
      <header class="d-flex justify-content-between align-items-center p-1">
        <div class="headerLeft">
          <router-link class="btn btn-primary" :to="{ name: 'HomePage' }"
            >Back</router-link
          >
        </div>
        <div class="headerRight d-flex gap-3">
          <a class="btn btn-info" href="#">Prev</a>
          <a class="btn btn-info" href="#">Next</a>
        </div>
      </header>
      <section>
        <div class="imageContainer">
          <img class="image" :src="photo.url" :alt="photo.title" />
        </div>
        <div class="userControl d-flex gap-3 p-1">
          <a class="btn btn-warning" href="#">Edit</a>
          <a class="btn btn-info" href="#">Publish</a>
          <a class="btn btn-danger" href="#">Delete</a>
        </div>
        <div>{{ getCategories(photo) }}</div>
      </section>
    </div>
  </div>
</template>

<style lang="scss" scoped>
  .myDetailedCard {
    padding: 1rem;
    width: 70%;
    height: 70%;
    position: relative;
  }

  .myDetailedCard header {
    position: absolute;
    left: 1rem;
    right: 1rem;
  }

  .image {
    width: 100%;
    height: 100%;
    display: block;
  }
  .userControl {
    position: absolute;
    left: 1rem;
    translate: 0 -100%;
  }
</style>
