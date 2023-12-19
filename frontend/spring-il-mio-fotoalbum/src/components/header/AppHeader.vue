<script>
  import { store } from "@/assets/data/store";

  export default {
    name: "AppHeader",
    data() {
      return {
        store,
      };
    },
    components: {},
    props: {},
    computed: {
      logBtn() {
        if (store.isLoggedIn) {
          return "Logout";
        }
        return "Login";
      },
      isLogged() {
        return store.isLoggedIn;
      },
      userLog() {
        if (this.isLogged) {
          return store.user.username;
        }
        return "Guest";
      },
    },
    methods: {
      logout() {
        store.isLoggedIn = false;
        store.user = {};
        this.$router.push({ name: "HomePage" });
      },
    },
  };
</script>

<template>
  <header>
    <nav class="d-flex justify-content-between align-items-center myNavbar">
      <div class="navbarLeft">
        <ul class="d-flex gap-3">
          <li>
            <router-link :to="{ name: 'HomePage' }">All Photos</router-link>
          </li>
          <li v-if="isLogged">
            <router-link :to="{ name: 'UserPage' }">My Photos</router-link>
          </li>
          <li>
            <router-link :to="{ name: 'CategoryPage' }">Categories</router-link>
          </li>
        </ul>
      </div>
      <div class="navbarRight">
        <ul class="d-flex gap-3">
          <li>{{ userLog }}</li>
          <li v-if="!isLogged">
            <router-link :to="{ name: 'LoginPage' }">Login</router-link>
          </li>
          <li v-if="isLogged">
            <div class="logoutBtn" @click="logout">Logout</div>
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

  .logoutBtn {
    cursor: pointer;
  }
</style>
