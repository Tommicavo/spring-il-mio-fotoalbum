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
        errors: [],
      };
    },
    components: {},
    props: {},
    computed: {},
    methods: {
      async login() {
        const endpoint = "http://127.0.0.1:8080/api/auth/login";
        this.errors = [];
        try {
          const statusCode = {
            validateStatus: function (status) {
              return status < 500;
            },
          };

          const res = await axios.post(endpoint, this.form, statusCode);

          console.log("RES: ", res);

          if (res.status != 200) {
            this.errors = res.data.map((error) => ({
              field: error.field,
              message: error.message,
            }));
            console.log("Field Errors: ", this.errors);
            return;
          }

          store.user = res.data;
          store.isLoggedIn = true;
          this.$router.push({ name: "HomePage" });
        } catch (err) {
          console.error("Catch Error: ", err);
        }
      },
      getFieldErrors(fieldName) {
        return this.errors
          .filter((error) => error.field === fieldName)
          .map((error) => error.message);
      },
    },
  };
</script>

<template>
  <form method="POST" @submit.prevent="login">
    <div class="username mb-3">
      <label for="username" class="form-label">Username</label>
      <input
        :class="{ 'is-invalid': getFieldErrors('username').length > 0 }"
        type="text"
        class="form-control"
        id="username"
        placeholder="Username"
        v-model.trim="form.username"
      />
      <div class="usernameError">
        <ul>
          <li
            class="errorMsg"
            v-for="error in getFieldErrors('username')"
            :key="error"
          >
            {{ error }}
          </li>
        </ul>
      </div>
    </div>
    <div class="password mb-3">
      <label for="password" class="form-label">Password</label>
      <input
        :class="{ 'is-invalid': getFieldErrors('password').length > 0 }"
        type="text"
        class="form-control"
        id="password"
        placeholder="Password"
        v-model.trim="form.password"
      />
      <div class="passwordError">
        <ul>
          <li
            class="errorMsg"
            v-for="error in getFieldErrors('password')"
            :key="error"
          >
            {{ error }}
          </li>
        </ul>
      </div>
    </div>
    <button type="submit" class="btn btn-primary">Login</button>
  </form>
</template>

<style lang="scss" scoped>
  .errorMsg {
    font-size: 1rem;
    font-weight: 500;
    padding-left: 10px;
    color: #dc3545;
  }
</style>
