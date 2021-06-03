import React from "react";
import Hero from "../components/Hero";
import Banner from "../components/Banner";
import { Link } from "react-router-dom";
import NavbarManager from "../components/Navbar-manager";

class Confirmation extends React.Component {

    render() {
        return (
            <>
                <NavbarManager/>
                <Hero hero="roomsHero">
                    <Banner title="Successful">
                        <Link to="/manager" className="btn-primary">
                            return home
                        </Link>
                    </Banner>
                </Hero>
            </>
        );
    }
}

export default Confirmation;