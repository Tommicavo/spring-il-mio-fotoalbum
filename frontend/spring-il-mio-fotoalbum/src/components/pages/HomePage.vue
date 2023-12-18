<script>
  import axios from "axios";
  import { store } from "@/assets/data/store.js";

  import PhotoCard from "@/components/photo/PhotoCard.vue";

  export default {
    name: "HomePage",
    data() {
      return {
        store,
        searchedWord: "",
        photos: [],
        filteredPhotos: [],
      };
    },
    components: {
      PhotoCard,
    },
    props: {},
    computed: {},
    methods: {
      async fetchPhotos() {
        const endpoint = "http://127.0.0.1:8080/photos/api";
        try {
          const res = await axios.get(endpoint);
          console.log("Photos: ", res.data);
          this.photos = res.data;
          this.filteredPhotos = res.data;
        } catch (err) {
          console.error("Catch Error: ", err);
        }
      },
      fetchFilteredPhotos() {
        if (this.searchedWord.length == 0) {
          this.filteredPhotos = [...this.photos];
          return;
        }
        const filteredPhotos = this.photos.filter((photo) =>
          photo.title.includes(this.searchedWord)
        );
        console.log(filteredPhotos);
        this.filteredPhotos = filteredPhotos;
      },
    },
    mounted() {
      this.fetchPhotos();
    },
  };
</script>

<template>
  <header class="d-flex justify-content-between align-items-center">
    <h1 class="myTitle">Foto Album</h1>
    <div class="searchBar">
      <div class="input-group">
        <form @submit.prevent="fetchFilteredPhotos" class="d-flex gap-2">
          <input
            type="text"
            class="form-control"
            placeholder="Search a Photo..."
            v-model.trim="searchedWord"
          />
          <button class="btn btn-primary" type="submit">Find</button>
        </form>
      </div>
    </div>
    <router-link
      v-if="store.isLoggedIn"
      class="btn btn-success"
      :to="{
        name: 'FormPage',
        params: { mode: 'create', id: 'new_photo' },
      }"
      >Add</router-link
    >
  </header>
  <div class="homePage">
    <div class="row">
      <div class="col" v-for="photo in filteredPhotos" :key="photo.id">
        <PhotoCard :photo="photo" />
      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
  @use "@/assets/sass/_vars.scss" as *;
  .homePage {
    height: calc(100% - $headerHeight - $titleHeight);
    overflow-y: auto;
    overflow-x: clip;
  }
</style>
