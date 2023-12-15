<script>
  import AppHeader from "@/components/header/AppHeader.vue";
  export default {
    name: "App",
    data() {
      return {};
    },
    components: {
      AppHeader,
    },
    props: {},
    computed: {},
    methods: {},
  };
</script>

<template>
  <div class="container h-100">
    <AppHeader />
    <main>
      <router-view></router-view>
    </main>
  </div>
</template>

<style lang="scss">
  @use "./assets/sass/style.scss" as *;
</style>
