import React, {Component} from "react";
import logo from '../images/logo.svg'
import {FaAlignRight} from 'react-icons/fa'
import {Link} from 'react-router-dom'

export default class NavbarManager extends Component {
    state = {
        isOpen: false
    };
    handleToggle = () => {
        this.setState({ isOpen: !this.state.isOpen });
    };
    render() {
        return (
            <nav className="navbar">
                <div className="nav-center">
                    <div className="nav-header">
                        <Link to="/manager">
                            <img src={logo} alt="Beach Resort" />
                        </Link>
                        <button
                            type="button"
                            className="nav-btn"
                            onClick={this.handleToggle}
                        >
                            <FaAlignRight className="nav-icon" />
                        </button>
                    </div>
                    <ul className={this.state.isOpen ? "nav-links show-nav" : "nav-links"}>
                        <li>
                            <Link to="/assign-food-order">Assign Order</Link>
                        </li>
                        <li>
                            <Link to="/assign-duty">Assign Duty</Link>
                        </li>
                        <li>
                            <Link to="/create-event">Create Event</Link>
                        </li>
                        <li>
                            <Link to="/evaluate-training">Training Application</Link>
                        </li>
                        <li>
                            <Link to="/evaluate-leave">Leave Application</Link>
                        </li>
                    </ul>
                </div>
            </nav>
        );
    }
}