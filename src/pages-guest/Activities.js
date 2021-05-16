import React from "react";
import Hero from "../components/Hero";
import Banner from "../components/Banner";
import { Link } from "react-router-dom";
import Navbar from "../components/Navbar";
import Table from "../components/Table";
import Title from "../components/Title";

const Activities = () => {
    return (
        <>
            <Navbar/>
            <Hero hero="roomsHero">
                <Banner title="Apply for activities">
                    <Link to="/home" className="btn-primary">
                        return home
                    </Link>
                </Banner>
            </Hero>
            <Table/>

        </>
    );
}

export default Activities;