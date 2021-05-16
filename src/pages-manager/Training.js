import React, {Component} from 'react'
import Hero from '../components/Hero';
import {Link} from "react-router-dom";
import Banner from "../components/Banner";
import NavbarManager from "../components/Navbar-manager";

export default class Training extends Component {

    async handleClick(){

        const url = "http://localhost:8080/deliverOrder/"+ 72086; //this.state.orderID
        let result = await fetch(url, {method:'PUT',
            headers:{
                "Content-Type":"application/json",
                "Accept":"application/json"
            },

        }).then( response => {
            this.setState(
                {
                    loading :true,

                }
            );

        })

    };

    constructor(props) {
        super(props) //since we are extending class Table so we have to use super in order to override Component class constructor
        this.state = { //state is by default an object
            events: [
                {housekeeper: 'HK3', Training: 'Group Tour DTO', date: '16/05/2021', status: 'waiting'},
                {housekeeper: 'Micheal Scott', Training: 'Group Tour DTO', date: '28/05/2021', status: 'waiting'},
                ]
        }
    }

    shoot() {
        alert("Training Accepted!");
    }

    shoot() {
        alert("Training Rejected!");
    }

    renderTableData() {
        return this.state.events.map((student, index) => {
            const {housekeeper, Training, date, status} = student
            return (
                <tr key={housekeeper}>
                    <td>{housekeeper}</td>
                    <td>{Training}</td>
                    <td>{date}</td>
                    <td>{status}</td>
                    <td><button onClick={this.shoot} className="btn-primary">Accept</button>
                    </td>
                    <td><button onClick={this.shoot2} className="btn-primary">Reject</button>
                    </td>
                </tr>
            )
        })
    }

    renderTableHeader() {
        let header = Object.keys(this.state.events[0])
        return header.map((key, index) => {
            return <th key={index}>{key.toUpperCase()}</th>

        })
    }

    render() {
        return (
            <>
                <NavbarManager/>
                <Hero hero="roomsHero">
                    <Banner title="Evaluate Training Application">
                        <Link to="/manager" className="btn-primary">
                            return home
                        </Link>
                    </Banner>
                </Hero>
                <div>
                    <table id='students'>
                        <tbody>
                        <tr>{this.renderTableHeader()}</tr>
                        {this.renderTableData()}
                        </tbody>
                    </table>
                </div>
            </>
        );
    }
}