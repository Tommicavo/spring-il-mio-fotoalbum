<script>
  export default {
    name: "PhotoCard",
    data() {
      return {};
    },
    components: {},
    props: {
      photo: Object,
    },
    computed: {},
    methods: {
      getCategories(photo) {
        let output = "";
        const categories = photo.categories;
        categories.forEach((category) => {
          output += category.label + ", ";
        });
        return output.substring(0, output.length - 2);
      },
    },
  };
</script>

<template>
  <div class="card myCard">
    <router-link :to="{ name: 'DetailPage', params: { id: photo.id } }">
      <div class="previewImg">
        <img :src="photo.url" :alt="photo.name" />
      </div>
      <div class="card-body">
        <h5 class="card-title text-center py-2">{{ photo.title }}</h5>
        <div v-if="photo.categories.length > 0" class="categories">
          <div class="fw-bold">Categories:</div>
          <div>
            {{ getCategories(photo) }}
          </div>
          <div class="user">
            <span class="fw-bold">Author: </span>
            <span>{{ photo.user.username }}</span>
          </div>
        </div>
      </div>
    </router-link>
  </div>
</template>

<style lang="scss" scoped>
  @use "@/assets/sass/_vars.scss" as *;
  .myCard {
    width: 450px;
    height: 500px;
    margin: 2rem auto;
    padding: 1rem;
    background-color: transparent;
    color: white;
    border: 4px solid white;
    border-radius: 2rem;

    &:hover {
      background-color: rgb(228, 223, 223);
      border: 4px solid transparent;

      & .card-body {
        color: black;
      }
    }
  }

  .card-body {
    height: 200px;
  }

  .myTitle {
    text-align: center;
    height: $titleHeight;
    display: flex;
    justify-content: center;
    align-items: center;
  }

  .previewImg {
    width: 100%;
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
</style>
