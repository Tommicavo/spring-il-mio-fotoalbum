<script>
  import { store } from "@/assets/data/store.js";

  import axios from "axios";

  export default {
    name: "LoginPage",
    data() {
      return {
        store,
        form: {
          username: "",
          password: "",
        },
      };
    },
    components: {},
    props: {},
    computed: {},
    methods: {
      async login() {
        const endpoint = "http://127.0.0.1:8080/api/auth/login";
        try {
          const res = await axios.post(endpoint, this.form);
          store.user = res.data;
          store.isLoggedIn = true;
          this.$router.push({ name: "HomePage" });
        } catch (err) {
          console.error("Catch Error: ", err);
        }
      },
    },
  };
</script>

<template>
  <form method="POST" @submit.prevent="login">
    <div class="username mb-3">
      <label for="username" class="form-label">Username</label>
      <input
        type="text"
        class="form-control"
        id="username"
        placeholder="Username"
        v-model.trim="form.username"
      />
    </div>
    <div class="password mb-3">
      <label for="password" class="form-label">Password</label>
      <input
        type="text"
        class="form-control"
        id="password"
        placeholder="Password"
        v-model.trim="form.password"
      />
    </div>
    <button type="submit" class="btn btn-primary">Login</button>
  </form>
</template>

<style lang="scss" scoped></style>
