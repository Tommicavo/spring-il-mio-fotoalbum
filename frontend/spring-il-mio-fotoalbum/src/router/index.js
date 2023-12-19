import { createRouter, createWebHistory } from "vue-router";

import HomePage from '@/components/pages/HomePage.vue';
import NotFoundPage from '@/components/pages/NotFoundPage.vue';

import DetailPage from '@/components/pages/DetailPage.vue';
import FormPage from '@/components/pages/FormPage.vue';
import LoginPage from '@/components/pages/LoginPage.vue';
import CategoryPage from '@/components/pages/CategoryPage.vue';
import UserPage from '@/components/pages/UserPage.vue';

const router = createRouter({
    history: createWebHistory(),
    linkExactActiveClass: 'active',
    routes: [
        {path: '/', name: 'HomePage', component: HomePage},
        {path: '/login', name: 'LoginPage', component: LoginPage},
        {path: '/user', name: 'UserPage', component: UserPage},
        {path: '/categories', name: 'CategoryPage', component: CategoryPage},
        {path: '/photos/:id', name: 'DetailPage', component: DetailPage},
        {path: '/photos/:mode/:id', name: 'FormPage', component: FormPage},

        {path: '/notFoundPage', name: 'NotFoundPage', component: NotFoundPage},
        {path: '/:pathMatch(.*)*', redirect: '/NotFoundPage'},
    ] 
});

export { router }
