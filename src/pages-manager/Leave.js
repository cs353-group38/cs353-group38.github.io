import React from "react";
import Hero from "../components/Hero";
import { Link } from "react-router-dom";
import Title from "../components/Title";
import NavbarManager from "../components/Navbar-manager";

function orderItems(event) {
    return(
        <div>
            {event}
        </div>
    )
}

class Leave extends React.Component {
    state = {
        loading: true,
        order: null,
        arr: []
    }

    async componentDidMount() {
        const url = "http://localhost:8080/viewLeaveRequests";
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

    shoot1() {
        alert("Cannot change");
    }

    shoot2() {
        alert("Leave application accepted");
    }

    shoot3() {
        alert("Leave application rejected");
    }

    render() {
        return (
            <Hero>
                <section className="services">
                    <Title title="Evaluate Leave Applications"/>
                    <div className="services-center">
                        <NavbarManager/>
                        <div>
                            <h5>Employee Name</h5>
                            {this.state.arr.map(food => orderItems(food['employeeName']))}
                        </div>
                        <div>
                            <h5>Employee ID</h5>
                            {this.state.arr.map(food => orderItems(food['employeeId']))}
                        </div>
                        <div>
                            <h5>Status</h5>
                            {this.state.arr.map(food => orderItems(food['status']))}
                        </div>
                        <div>
                            <br/>
                            <br/>
                            <button onClick={this.shoot1} className="btn-primary">No change</button>
                            <br/>
                            <button onClick={this.shoot2} className="btn-primary">Accept</button>
                            <button onClick={this.shoot3} className="btn-primary">Reject</button>
                        </div>

                        <div>
                            <button>
                                <Link to='/home/' className='btn-primary'>
                                    Back
                                </Link>
                            </button>
                        </div>
                    </div>
                </section>
            </Hero>
        )
    }
}

export default Leave;