import React, {Component} from 'react'
import Hero from '../components/Hero';
import {Link} from "react-router-dom";
import Banner from "../components/Banner";
import NavbarManager from "../components/Navbar-manager";

export default class AssignOrder extends Component {

    constructor(props) {
        super(props) //since we are extending class Table so we have to use super in order to override Component class constructor
        this.state = { //state is by default an object
            events: [
                {housekeeper: 'HK3', Room: 'W104', Order: '10', date: '16/05/2021'},
                {housekeeper: 'Micheal Scott', Room: 'E702', Order: '12', date: '28/05/2021'},
                {housekeeper: 'HK3', Room: 'E416', Order: '16', date: '06/06/2021'},
                {housekeeper: 'Micheal Scott', Room: 'W112', Order: '21', date: '14/05/2021'}
            ]
        }
    }

    shoot() {
        alert("OrderAssigned!");
    }

    renderTableData() {
        return this.state.events.map((student, index) => {
            const {housekeeper, Room, Order, date} = student
            return (
                <tr key={housekeeper}>
                    <td>{housekeeper}</td>
                    <td>{Room}</td>
                    <td>{Order}</td>
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
                    <Banner title="Assign Order">
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