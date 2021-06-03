import React from "react";
import Hero from "../components/Hero";
import Banner from "../components/Banner";
import {Link} from 'react-router-dom'
import Services from "../components/Services";
import Navbar from "../components/Navbar";

export default function Home() {
    return (
        <>
            <Navbar/>
            <Hero>
                <Banner title="Make a Reservation"
                        subtitle="deluxe rooms with low starting prices">
                    <Link to='/rooms' className="btn-primary">
                        View rooms
                    </Link>
                    <Link to='/' className='btn-primary'>
                        Logout
                    </Link>
                </Banner>
            </Hero>
            <Services />
        </>
    )
}