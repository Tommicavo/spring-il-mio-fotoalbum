<script>
  import axios from "axios";

  import DetailedPhotoCard from "@/components/photo/DetailedPhotoCard.vue";

  export default {
    name: "DetailPage",
    data() {
      return {
        photo: {},
      };
    },
    components: {
      DetailedPhotoCard,
    },
    props: {},
    computed: {},
    methods: {
      async fetchPhoto() {
        const endpoint = `http://127.0.0.1:8080/photos/api/${this.$route.params.id}`;
        try {
          const res = await axios.get(endpoint);
          this.photo = res.data;
          // console.log("PHOTO:\n", this.photo);
        } catch (err) {
          console.error("Catch Error: ", err);
        }
      },
      async deletePhoto(id) {
        const endpoint = `http://127.0.0.1:8080/photos/api/${id}`;
        try {
          const res = await axios.delete(endpoint);
          // console.log(res.data);
          this.$router.push({ name: "HomePage" });
        } catch (err) {
          console.error("Catch Error: ", err);
        }
      },
      async changeVisibility(id) {
        const endpoint = `http://127.0.0.1:8080/photos/api/${id}/visibility`;
        const data = { visible: !this.photo.visible };

        try {
          const res = await axios.patch(endpoint, data);
          this.photo = res.data;
          this.$router.push({ name: "HomePage" });
        } catch (err) {
          console.error("Catch Error: ", err);
        }
      },
    },
    mounted() {
      this.fetchPhoto();
    },
  };
</script>

<template>
  <div class="detailPage d-flex justify-content-center align-items-center">
    <DetailedPhotoCard
      :photo="photo"
      @delete="deletePhoto"
      @visibleBtn="changeVisibility"
    />
  </div>
</template>

<style lang="scss" scoped></style>
