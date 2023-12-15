<script>
  export default {
    name: "AppHeader",
    data() {
      return {};
    },
    components: {},
    props: {},
    computed: {},
    methods: {},
  };
</script>

<template>
  <header>
    <nav class="d-flex justify-content-between align-items-center myNavbar">
      <div class="navbarLeft">
        <ul class="d-flex gap-3">
          <li>
            <router-link :to="{ name: 'HomePage' }">Photos</router-link>
          </li>
          <li>
            <router-link :to="{ name: 'HomePage' }">Categories</router-link>
          </li>
        </ul>
      </div>
      <div class="navbarRight">
        <ul class="d-flex gap-3">
          <li>
            <router-link :to="{ name: 'HomePage' }">Login</router-link>
          </li>
        </ul>
      </div>
    </nav>
  </header>
</template>

<style lang="scss" scoped>
  @use "@/assets/sass/_vars.scss" as *;
  .myNavbar {
    height: $headerHeight;
  }
</style>
