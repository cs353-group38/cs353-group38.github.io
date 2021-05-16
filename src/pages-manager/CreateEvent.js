import React, {useState, useEffect} from "react";
import Hero from '../components/Hero';
import Banner from "../components/Banner";
import {Link, useHistory} from 'react-router-dom';

import NavbarManager from "../components/Navbar-manager";
import ServicesManager from "../components/Services-Manager";

function orderItems(event) {
    return(
        <div>
            {event}
        </div>
    )
}

export default function CreateEvent() {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const history = useHistory();
    useEffect(() => {
        if (localStorage.getItem('user-info')) {

        }
    }, [])

    async function login() {
        const url = "http://localhost:8080/createActivity";
        const response = await fetch(url, {
            method: 'POST',
            headers: {
                "Content-Type": "application/json",
                "Accept": "application/json"
            },
            body: {email, password},

        }).then(response => {
                response.json().then(data => {
                    this.setState({
                        arr: data
                    });
                    history.push("/reservation")
                });
            }
        );
    }

    return (
        <>
            <NavbarManager/>
            <Hero hero="roomsHero">
                <Banner title="Create Event">
                </Banner>
            </Hero>
            <form>
                <table id="students">
                    <h4>Enter Event Name</h4>
                    <input type="text" placeholder="event name" onChange={(e) => setEmail(e.target.value)}
                           className="form-control"/>
                    <h4>Enter Event Location</h4>
                    <input type="text" placeholder="event location" onChange={(e) => setPassword(e.target.value)}
                           className="form-control"/>
                    <h4>Enter Event Start Date</h4>
                    <input type="text" placeholder="event start date" onChange={(e) => setEmail(e.target.value)}
                           className="form-control"/>
                    <h4>Enter Event End Date</h4>
                    <input type="text" placeholder="event end date" onChange={(e) => setPassword(e.target.value)}
                           className="form-control"/>
                    <h4>Enter Event Quota</h4>
                    <input type="text" placeholder="event quota" onChange={(e) => setEmail(e.target.value)}
                           className="form-control"/>
                    <h4>Enter Event Price</h4>
                    <input type="text" placeholder="event price" onChange={(e) => setPassword(e.target.value)}
                           className="form-control"/>

                    <button onClick={login} className="btn-primary">Confirm</button>
                    <button>
                        <Link to='/manager' className='btn-primary'>
                            Back
                        </Link>
                    </button>
                </table>
            </form>
        </>
    )
}
