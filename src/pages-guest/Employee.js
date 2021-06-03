import React from "react";
import Hero from '../components/Hero';
import Banner from '../components/Banner'
import {Link} from 'react-router-dom'

export default function Employee(){
    return <Hero>
        <Banner title="Welcome Employee"
                subtitle="Manager, Security, or Housekeeper?" >
            <Link to='/LoginManager/' className='btn-primary'>
                Manager
            </Link>
            <Link to='/LoginToHousekeeper/' className='btn-primary'>
                Housekeeper
            </Link>
            <Link to='/LoginToSecurity/' className='btn-primary'>
                Security
            </Link>
            <br/>
            <br/>
            <Link to='/' className='btn-primary'>
                Back
            </Link>
        </Banner>
    </Hero>;
}