import React from "react";
import Hero from "../components/Hero";
import Banner from "../components/Banner";

import ServicesManager from "../components/Services-Manager";
import NavbarManager from "../components/Navbar-manager";
import {Link} from "react-router-dom";

export default function Home() {
    return (
        <>
            <NavbarManager/>
            <Hero>
                <Banner title="Welcome, Manager">
                    <Link to='/' className='btn-primary'>
                        Logout
                    </Link>
                </Banner>
            </Hero>
            <ServicesManager />
        </>
    )
}
