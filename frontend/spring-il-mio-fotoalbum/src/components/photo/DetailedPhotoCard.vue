<script>
  import { store } from "@/assets/data/store.js";

  export default {
    name: "DetailedPhotoCard",
    data() {
      return {
        store,
      };
    },
    components: {},
    props: {
      photo: Object,
    },
    computed: {
      isMyPhoto() {
        if (this.photo && this.photo.user) {
          return store.user.username == this.photo.user.username;
        }
      },
    },
    methods: {
      getCategories(photo) {
        if (this.photo && this.photo.categories) {
          let output = "";
          const categories = photo.categories;
          categories.forEach((category) => {
            output += "#" + category.label + " ";
          });
          return output;
        }
      },
      getAuthor(photo) {
        if (this.photo && this.photo.user) {
          return photo.user.username;
        }
      },
    },
    emits: ["delete"],
    watch: {
      photo: {
        immediate: false,
        handler(newVal) {
          console.log("Photo: ", newVal);
        },
      },
    },
  };
</script>

<template>
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
      <div v-if="isMyPhoto" class="userControl d-flex gap-3 p-1">
        <router-link
          class="btn btn-warning"
          :to="{
            name: 'FormPage',
            params: { mode: 'edit', id: photo.id },
          }"
          >Edit</router-link
        >
        <a class="btn btn-info" href="#">Publish</a>
        <button
          class="btn btn-danger"
          type="button"
          @click="$emit('delete', photo.id)"
        >
          Delete
        </button>
      </div>
      <div class="categories">
        <span class="fw-bold">About: </span>
        <span>{{ getCategories(photo) }}</span>
      </div>
      <div class="author">
        <span class="fw-bold">Author: </span>
        <span>{{ getAuthor(photo) }}</span>
      </div>
    </section>
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
    border-radius: 1rem;
  }
  .userControl {
    position: absolute;
    left: 1rem;
    translate: 0 -100%;
  }
</style>
