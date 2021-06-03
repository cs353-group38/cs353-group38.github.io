import React, {useState, useEffect} from "react";
import Hero from '../components/Hero';
import {useHistory} from 'react-router-dom'
import Banner from '../components/Banner'
import { withRouter } from 'react-router';
import {Link} from 'react-router-dom'

export default function LoginToHousekeeper() {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const history = useHistory();
    useEffect(() => {
        if (localStorage.getItem('user-info')) {

        }
    }, [])

    async function login() {
        console.warn(email, password);
        let item = {email, password};
        let result = await fetch("http://localhost:8080/login/GUEST/" + item.email + "/" + item.password, {
            method: 'GET',
            headers: {
                "Content-Type": "application/json",
                "Accept": "application/json"
            },


        }).then(response => {
                response.json().then(data => {
                    localStorage.setItem("id", data['id'])
                });

                if (response.status == 200) {
                    history.push("/home")
                } else {
                    window.alert("Login Failed. Please enter correct email and password")
                }
            }
        );

        //eslint-disable-next-line no-restricted-globals
    }

    return (
        <>
            <Hero>
                <Banner title="Login">
                    <div className="col-sm-6">
                        <input type="text" placeholder="email" onChange={(e) => setEmail(e.target.value)}
                               className="form-control"/>
                        <br/>
                        <input type="password" placeholder="password" onChange={(e) => setPassword(e.target.value)}
                               className="form-control"/>
                    </div>
                    <br/>
                    <button onClick={login} className="btn-primary">Login</button>

                    <button>
                        <Link to='' className='btn-primary'>
                            Back
                        </Link>
                    </button>
                </Banner>
            </Hero>
        </>);
}
