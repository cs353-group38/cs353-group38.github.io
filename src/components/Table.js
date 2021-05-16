import React, { Component } from 'react'
import {Link} from "react-router-dom";

class Table extends Component {
    constructor(props) {
        super(props) //since we are extending class Table so we have to use super in order to override Component class constructor
        this.state = { //state is by default an object
            events: [
                { name: 'Group Tour', location: 'West-wing', date: '16/05/2021', price: 55 },
                { name: 'Beach Party', location: 'Eastside', date: '28/05/2021', price: 120 },
                { name: 'Stadium Tour', location: 'South-west', date: '06/06/2021', price: 60 },
                { name: 'Musical Evening', location: 'Eastside', date: '14/05/2021', price: 180 }
            ]
        }
    }

    shoot() {
        alert("Activity Successfully Selected!");
    }

    renderTableData() {
        return this.state.events.map((student, index) => {
            const { name, location, date, price } = student
            return (
                <tr key={name}>
                    <td>{name}</td>
                    <td>{location}</td>
                    <td>{date}</td>
                    <td>{price}</td>
                    <td><button onClick={this.shoot} className="btn-primary">Select</button>
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
            <div>
                <table id='students'>
                    <tbody>
                    <tr>{this.renderTableHeader()}</tr>
                    {this.renderTableData()}
                    </tbody>
                </table>
            </div>
        )
    }
}

export default Table