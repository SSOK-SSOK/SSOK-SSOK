<template>
  <v-container fluid fill-height>
    <div class="background">
      <div id="stars"></div>
      <div id="stars2"></div>
      <div id="stars3"></div>
      <Cosmos />
    </div>
    <div class="contents">
      <nav>
        <div class="logo">
          <img
            class="avatar"
            src="@/assets/images/welcome-icon.png"
            alt="welcome-icon"
          />
          <p>SSOK SSOK</p>
        </div>
        <v-tooltip bottom color="deep-purple accent-3">
          <template v-slot:activator="{ on, attrs }">
            <div
              v-bind="attrs"
              v-on="on"
              class="glow mx-2"
              @click="moveMainPage"
            ></div>
          </template>
          <span>게임 선택 GO GO!</span>
        </v-tooltip>
      </nav>
      <div class="loginZone d-flex align-items-center">
        <div>
          <p>
            {{ user }}
            다양한 언어를
            <br />
            재밌게 배워보아요!
          </p>
          <button v-if="getCheckLogin" class="auth-button" @click="logout">
            Logout
          </button>
          <button v-else class="auth-button" @click="google">Google</button>
        </div>
      </div>
    </div>
  </v-container>
</template>

<script>
import Cosmos from "./Cosmos.vue";
import { GOOGLE_AUTH_URL, ACCESS_TOKEN } from "@/config/index.js";
import { mapState } from "vuex";
export default {
  name: "WelcomePage",
  components: {
    Cosmos,
  },
  data() {
    return {
      user: this.userName,
    };
  },
  mounted() {
    console.log("마운티드");
    this.getToken();
  },
  computed: {
    ...mapState("UserStore", ["userName"]),
    getCheckLogin() {
      return this.$store.getters["UserStore/getCheckLogin"];
    },
  },
  methods: {
    moveMainPage: function () {
      this.$router.push({ name: "MainPage" });
    },
    google() {
      window.location.href = GOOGLE_AUTH_URL;
    },
    getUrlParameter(name) {
      name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
      var regex = new RegExp("[\\?&]" + name + "=([^&#]*)");
      var results = regex.exec(window.location.search);

      return results === null
        ? ""
        : decodeURIComponent(results[1].replace(/\+/g, " "));
    },
    getUser() {
      if (localStorage.getItem(ACCESS_TOKEN)) {
        this.$store.dispatch("UserStore/fetchUser");
        this.$router.push({ name: "WelcomePage" }).catch(() => {});
      }
    },
    getToken() {
      const token = this.getUrlParameter("token");
      console.log(token);
      if (token) {
        localStorage.setItem(ACCESS_TOKEN, token);
      }
      this.getUser();
    },
    logout() {
      this.$store.dispatch("UserStore/logout");
    },
  },
};
</script>

<style lang="scss" scoped>
@import "@/style/star.sass";
@import "@/style/auth-button.scss";
@import "@/style/light-button.scss";
.container {
  padding: 1%;
  .background {
    position: absolute;
    z-index: -1;
    width: 100%;
    height: 100%;
    // Cosmos.vue
    // star.sass
  }
  .contents {
    position: absolute;
    width: 100%;
    height: 100%;
    z-index: 10;
    left: 0;
    padding: 1%;
    nav {
      display: flex;
      justify-content: space-between;
      align-items: center;
      padding: 2rem;
      margin-top: 1%;
      margin-bottom: 5%;
      height: 5%;
      width: 100%;
      color: white;
      background: none;
      font-size: 1rem;
      .logo {
        display: flex;
        align-items: center;
        width: 30%;
        padding: 0;
        .avatar {
          vertical-align: middle;
          border-radius: 50%;
          width: 15%;
          height: 15%;
        }
        p {
          font-size: 5vh;
          margin: 0 0 0 0.5vw;
        }
      }
    }
    .loginZone {
      width: 50%;
      height: 60%;
      position: relative;
      display: flex;
      align-items: center;
      justify-content: center;
      div {
        text-align: center;
      }
      p {
        font-size: 4rem;
        text-align: center;
        margin: 0;
      }
    }
  }
}
</style>
