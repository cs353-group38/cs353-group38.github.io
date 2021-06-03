import React, { Component } from "react";
import { FaUtensils, FaHiking, FaDoorOpen, FaHotel, FaFly, FaPen } from "react-icons/fa";
import Title from "./Title";
import {Link} from "react-router-dom";
export default class ServicesManager extends Component {
    state = {
        services: [
            {
                icon: <FaUtensils />,
                title: "Assign Food Order",
                info: "",
                link: "/assign-food-order",
            },
            // set up links
            {
                icon: <FaHiking />,
                title: "Evaluate Training Program Application",
                info: "",
                link: "/evaluate-training",
            },
            {
                icon: <FaFly />,
                title: "Create New Event",
                info: "",
                link: "/create-event",
            },
            {
                icon: <FaHotel />,
                title: "Manage Current Events",
                info: "",
                link: "/manage-event",
            },
            {
                icon: <FaPen />,
                title: "Assign Duty",
                info: "",
                link: "/assign-duty",
            },
            {
                icon: <FaDoorOpen />,
                title: "Evaluate Leave Applications",
                info: "",
                link: "/evaluate-leave",
            }
        ]
    };
    render() {
        return (
            <section className="services">
                <Title title="services" />
                <div className="services-center">
                    {this.state.services.map(item => {
                        return (
                            <article key={`item-${item.title}`} className="service">
                                <span>{item.icon}</span>
                                <h6>{item.title}</h6>
                                <p>{item.info}</p>
                                <Link to={item.link} className="btn-primary">
                                    visit
                                </Link>
                            </article>
                        );
                    })}
                </div>
            </section>
        );
    }
}