
import PatientRecordClient from '../api/patientRecordClient';

import BindingClass from "../util/bindingClass";

/**
 * The header component for the website.
 */
export default class Header extends BindingClass {
    constructor() {
        super();

        const methodsToBind = [
            'addHeaderToPage', 'createSiteTitle', 'createUserInfoForHeader',
            'createLoginButton', 'createLoginButton', 'createLogoutButton','createNavbar'
        ];
        this.bindClassMethods(methodsToBind, this);


        this.client = new PatientRecordClient();
    }

    /**
     * Add the header to the page.
     */
    async addHeaderToPage() {
        const currentUser = await this.client.getIdentity();

        const siteTitle = this.createSiteTitle();
        const userInfo = this.createUserInfoForHeader(currentUser);

        const header = document.getElementById('header');
        header.appendChild(siteTitle);
        header.appendChild(userInfo);
    }

    createSiteTitle() {
        const homeButton = document.createElement('a');
        homeButton.classList.add('header_home');
        homeButton.innerText = 'Patient Record Service';

        const siteTitle = document.createElement('div');
        siteTitle.classList.add('site-title');
        siteTitle.appendChild(homeButton);

        return siteTitle;
    }

    createUserInfoForHeader(currentUser) {
        const userInfo = document.createElement('nav');
        userInfo.classList.add('user');

        // Apply CSS style to make child elements display in a row
        userInfo.style.display = 'flex';
        userInfo.style.alignItems = 'center';

        const childContent = currentUser
            ? this.createLogoutButton(currentUser)
            : this.createLoginButton();
        const navbar = this.createNavbar();
        userInfo.appendChild(navbar);
        userInfo.appendChild(childContent);


        return userInfo;
    }

    createLoginButton() {
        return this.createButton('Login', this.client.login,'login-button');
    }

    createLogoutButton(currentUser) {
        return this.createButton(`Logout: ${currentUser.name}`, this.client.logout,'logout-button');
    }

    createButton(text, clickHandler,buttonClass) {
        const button = document.createElement('a');
        button.classList.add('button', buttonClass);
        button.classList.add('button');
        button.href = '#';
        button.innerText = text;

        button.addEventListener('click', async () => {
            await clickHandler();
        });

        return button;
    }

    createNavbar() {

        const homeButton = document.createElement('a');
        homeButton.classList.add('home');
        homeButton.href = 'index.html';
        homeButton.innerText = 'Home';
        homeButton.classList.add('nav-button');

        const siteTitle = document.createElement('div');
        siteTitle.classList.add('site-title');
        siteTitle.appendChild(homeButton);

        return siteTitle;


    }
}
