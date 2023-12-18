import { createRouter, createWebHistory } from "vue-router";

import HomePage from '@/components/pages/HomePage.vue';
import NotFoundPage from '@/components/pages/NotFoundPage.vue';

import DetailPage from '@/components/pages/DetailPage.vue';
import FormPage from '@/components/pages/FormPage.vue';

const router = createRouter({
    history: createWebHistory(),
    linkExactActiveClass: 'active',
    routes: [
        {path: '/', name: 'HomePage', component: HomePage},
        {path: '/photos/:id', name: 'DetailPage', component: DetailPage},
        {path: '/photos/:mode/:id', name: 'FormPage', component: FormPage},

        {path: '/notFoundPage', name: 'NotFoundPage', component: NotFoundPage},
        {path: '/:pathMatch(.*)*', redirect: '/NotFoundPage'},
    ] 
});

export { router }
