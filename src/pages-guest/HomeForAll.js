import React from "react";
import Hero from '../components/Hero';
import Banner from '../components/Banner'
import {Link} from 'react-router-dom'

export default function HomeForAll() {
    return <Hero>
        <Banner title="Welcome To Beach resort"
                subtitle="Please press Guest, Employee or Candidate" >
            <Link to='/LoginGuest' className='btn-primary'>
                Guest
            </Link>
            <Link to='/Employee/' className='btn-primary'>
                Employee
            </Link>
            <Link to='/Employee/' className='btn-primary'>
                Candidate
            </Link>
        </Banner>
    </Hero>;
}