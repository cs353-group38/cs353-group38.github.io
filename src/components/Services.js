import React, { Component } from "react";
import { FaUtensils, FaHiking, FaRegCommentDots, FaQuestionCircle } from "react-icons/fa";
import Title from "./Title";
import {Link} from "react-router-dom";
export default class Services extends Component {
    state = {
        services: [
            {
                icon: <FaUtensils />,
                title: "Order Food",
                info:
                    "Order food from a variety of restaurants and have it delivered to your room in no time!",
                link: "/Food",
            },
            // set up links
            {
                icon: <FaHiking />,
                title: "Tickets for Activities",
                info:
                    "Get tickets to activities. Activities consist of group tours, pool parties, beach volleyball.",
                link: "/Activities",
            },
            {
                icon: <FaRegCommentDots />,
                title: "Comment Card",
                info:
                    "Make a comment on your stay at our hotel. Your feedback is valuable.",
                link: "/Comments",
            },
            {
                icon: <FaQuestionCircle />,
                title: "Ask Questions",
                info:
                    "Get the answers to all your queries by asking questions to the lobby.",
                //link: "/Comment",
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