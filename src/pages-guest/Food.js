import React from "react";
import Hero from "../components/Hero";
import { Link } from "react-router-dom";
import Navbar from "../components/Navbar";
import Title from "../components/Title";

function orderItems(event) {
    return(
        <div>
            {event}
        </div>
    )
}

class Rooms extends React.Component {

    state = {
        loading: true,
        order: null,
        arr: []
    }

    async componentDidMount() {
        const url = "http://localhost:8080/foods";
        const response = await fetch(url, {
            method: 'GET',
            headers: {
                "Content-Type": "application/json",
                "Accept": "application/json"
            },

        }).then(response => {
                response.json().then(data => {
                    this.setState({
                        arr: data
                    });

                    for (let i = 0; i < this.state.arr.length; i++) {
                        console.log(this.state.arr[i])
                    }
                });
            }
        );
    }

    shoot() {
        alert("Order Confirmed!");
    }

    shoot1() {
        alert("Food added to cart!");
    }

    render() {
        return (
            <Hero>
                <section className="services">
                    <Title title="Order Food"/>
                    <div className="services-center">
                        <Navbar/>
                        <div>
                            <h5>Restaurant Name</h5>
                            {this.state.arr.map(food => orderItems(food['restaurantName']))}
                        </div>
                        <div>
                            <h5>Food Name</h5>
                            {this.state.arr.map(food => orderItems(food['foodName']))}
                        </div>
                        <div>
                            <h5>Food Price</h5>
                            {this.state.arr.map(food => orderItems(food['price']))}
                        </div>
                        <div>
                            <br/>
                            <br/>
                            <button onClick={this.shoot1} className="btn-primary">Choose</button>
                            <br/>
                            <button onClick={this.shoot1} className="btn-primary">Choose</button>
                            <br/>
                            <button onClick={this.shoot1} className="btn-primary">Choose</button>
                            <br/>
                        </div>

                            <div>
                                <button onClick={this.shoot} className="btn-primary">Confirm</button>

                                <br/>
                                <br/>
                            <button>
                                <Link to='/home/' className='btn-primary'>
                                    Back
                                </Link>
                            </button>
                        </div>
                    </div>
                </section>
            </Hero>
        );
    }
}

export default Rooms;