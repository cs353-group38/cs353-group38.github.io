import React, {Component} from 'react'
import  {useState, useEffect} from "react";
import Hero from '../components/Hero';
import {Link} from "react-router-dom";
import Navbar from "../components/Navbar";
import Banner from "../components/Banner";
import NavbarManager from "../components/Navbar-manager";

export default class AssignDuty extends Component {

    constructor(props) {
        super(props) //since we are extending class Table so we have to use super in order to override Component class constructor
        this.state = { //state is by default an object
            events: [
                {securitystaff: 'SS3', Building: 'B12', Location: 'West-wing', date: '22/10/2021'},
                {securitystaff: 'SS2', Building: 'E2', Location: 'West-wing', date: '08/01/2021'},
                {securitystaff: 'ms', Building: 'M16', Location: 'South', date: '31/01/2021'},
                //{securitystaff: 'Berry', Building: 'W112', Location: '21', date: '14/05/2021'}
            ]
        }
    }

    shoot() {
        alert("Duty Assigned!");
    }

    renderTableData() {
        return this.state.events.map((student, index) => {
            const {securitystaff, Building, Location, date} = student
            return (
                <tr key={securitystaff}>
                    <td>{securitystaff}</td>
                    <td>{Building}</td>
                    <td>{Location}</td>
                    <td>{date}</td>
                    <td><button onClick={this.shoot} className="btn-primary">Assign</button>
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
                    <Banner title="Assign Duty">
                        <Link to="/manager" className="btn-primary">
                            return home
                        </Link>
                    </Banner>
                </Hero>
                <div>
                    <h2 id='title'>Assign Security Walk</h2>
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