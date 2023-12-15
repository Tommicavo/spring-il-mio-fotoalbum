import { createApp } from 'vue'
import './style.css'
import App from './App.vue'

// Router
import { router } from './router/index.js';

// bootstrap
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.min.js';

// App
const app = createApp(App);
app.use(router);
app.mount('#app');
