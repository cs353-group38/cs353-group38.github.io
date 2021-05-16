import React, { Component } from "react";
import Title from "./Title";
import { RoomContext } from "../Context";
import Room from "./Room";
export default class FeaturedRooms extends Component {
    static contextType = RoomContext;

    render() {
        let {featuredRooms: rooms } = this.context;

        rooms = rooms.map(room => {
            return <Room key={room.id} room={room} />;
        });
        return (
            <section className="featured-rooms">
                <Title title="featured room types" />
                <div className="featured-rooms-center">
                    {rooms}
                </div>
            </section>
        );
    }
}