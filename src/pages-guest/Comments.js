import React from "react";
import Hero from "../components/Hero";
import Banner from "../components/Banner";
import { Link } from "react-router-dom";
import Navbar from "../components/Navbar";

class Comments extends React.Component {

    constructor(props){
        super(props);
        this.state = { restaurant: '', order: ''};
    }

    handleChange = ({ target }) => {
        this.setState({ [target.name]: target.value });
    };

    shoot() {
        alert("Comment Complete!");
    }

    render() {
        return (
            <>
                <Navbar/>
                <Hero hero="roomsHero">
                    <Banner title="Comment">
                        <h5>Guest Name:
                            <input
                                type="text"
                                name="restaurant"
                                value={this.state.restaurant}
                                onChange={this.handleChange}
                            />
                        </h5>

                        <h5>Comment:
                            <input
                                type="text"
                                name="order"
                                value={this.state.order}
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
            </>
        );
    }
}

export default Comments;