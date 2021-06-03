import React, {useState, useEffect} from "react";
import Hero from "../components/Hero";
import Banner from "../components/Banner";
import { Link } from "react-router-dom";
import FeaturedRooms from "../components/FeaturedRooms";
import Navbar from "../components/Navbar";

class Rooms extends React.Component {


    constructor(props) {
        super(props);
        this.state = {checkin: '', checkout: '', roomtype: '',};
    }

    handleChange = ({target}) => {
        this.setState({[target.name]: target.value});
    };

    shoot() {
        alert("Reservation Complete!");
    }

    render() {
        return (
            <>
                <Navbar/>
                <Hero hero="roomsHero">
                    <Banner title="Reservation">
                        <h5>Enter Check-in Date:
                            <input
                                type="text"
                                name="checkin"
                                value={this.state.checkin}
                                onChange={this.handleChange}
                            />
                        </h5>

                        <h5>Enter Check-out Date:
                            <input
                                type="text"
                                name="checkout"
                                value={this.state.checkout}
                                onChange={this.handleChange}
                            />
                        </h5>

                        <h5>Enter Room Type:
                            <input
                                type="text"
                                name="roomtype"
                                value={this.state.roomtype}
                                onChange={this.handleChange}
                            />
                        </h5>

                        <button onClick={this.shoot} className="btn-primary">Confirm</button>

                        <button>
                            <Link to="/home" className="btn-primary">
                                return home
                            </Link>
                        </button>
                    </Banner>
                </Hero>
                <FeaturedRooms/>

            </>
        );
    }
}

export default Rooms;