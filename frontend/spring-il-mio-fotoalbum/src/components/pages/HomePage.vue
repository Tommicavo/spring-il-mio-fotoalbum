<script>
  import axios from "axios";

  import PhotoCard from "@/components/photo/PhotoCard.vue";

  export default {
    name: "HomePage",
    data() {
      return {
        photos: [],
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
          // console.log("Photos: ", res.data);
          this.photos = res.data;
        } catch (err) {
          console.error("Catch Error: ", err);
        }
      },
    },
    mounted() {
      this.fetchPhotos();
    },
  };
</script>

<template>
  <header>
    <h1 class="myTitle">Foto Album</h1>
    <router-link class="btn btn-success" :to="{ name: 'CreatePage' }"
      >Add</router-link
    >
  </header>
  <div class="homePage">
    <div class="row">
      <div class="col" v-for="photo in photos" :key="photo.id">
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
