<script>
  import axios from "axios";

  export default {
    name: "HomePage",
    data() {
      return {
        photos: [],
        output: "",
      };
    },
    components: {},
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
      getCategories(photo) {
        let output = "";
        const categories = photo.categories;
        categories.forEach((category) => {
          output += category.label + ", ";
        });
        return output.substring(0, output.length - 2);
      },
    },
    mounted() {
      this.fetchPhotos();
    },
  };
</script>

<template>
  <h1 class="myTitle">Foto Album</h1>
  <div class="homePage">
    <div class="row">
      <div class="col" v-for="photo in photos" :key="photo.id">
        <div class="card myCard">
          <div class="previewImg mx-auto">
            <img :src="photo.url" :alt="photo.name" />
          </div>
          <div class="card-body">
            <h5 class="card-title text-center py-2">{{ photo.title }}</h5>
            <div v-if="photo.categories.length > 0" class="categories">
              <div class="fw-bold">Categories:</div>
              <div>
                {{ getCategories(photo) }}
              </div>
            </div>
          </div>
          <div class="cardFooter">
            <footer class="d-flex justify-content-center gap-3 pt-3">
              <a href="#" class="btn btn-primary">Show</a>
              <a href="#" class="btn btn-warning">Edit</a>
              <a href="#" class="btn btn-danger">Delete</a>
            </footer>
          </div>
        </div>
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
  .myTable {
    border-radius: 2rem;
    overflow: hidden;
  }

  .previewImg {
    width: 300px;
    height: 300px;
    overflow: hidden;
  }

  .previewImg img {
    max-width: 100%;
    max-height: 100%;
    width: auto;
    height: auto;
    display: block;
    border-radius: 1rem;
  }

  .myCard {
    width: 450px;
    margin: 2rem auto;
    padding: 1rem;
    background-color: aqua;
    height: 500px;
  }

  .myTitle {
    text-align: center;
    height: $titleHeight;
    display: flex;
    justify-content: center;
    align-items: center;
  }
</style>
