import React from "react";
import './App.css';

import {Link, Route, Switch} from 'react-router-dom'

import HomeForAll from "./pages-guest/HomeForAll";
import Employee from "./pages-guest/Employee";

// guest pages
import Home from "./pages-guest/Home";
import Room from "./pages-guest/Rooms"
import Food from "./pages-guest/Food"
import SingleRoom from "./pages-guest/SingleRoom"
import Error from "./pages-guest/Error"
import Activities from "./pages-guest/Activities";
import Comments from "./pages-guest/Comments";
import LoginGuest from "./pages-guest/login-guest"
import LoginManager from "./pages-manager/login-manager"

// manager pages
import HomeManager from "./pages-manager/Home";
import OrderManager from "./pages-manager/AssignOrder"
import LeaveManager from "./pages-manager/Leave"
import CreateEvent from "./pages-manager/CreateEvent"
import Training from "./pages-manager/Training"
import AssignDuty from "./pages-manager/AssignDuty"
import Confirmation from "./pages-manager/Confirmation";

function App() {
    return (
        <><Switch>
            <Route exact path="/" component={HomeForAll}/>
            <Route exact path="/Employee/" component={Employee}/>

            <Route exact path="/LoginGuest" component={LoginGuest}/>
            <Route exact path="/home" component={Home}/>
            <Route exact path="/rooms/" component={Room}/>
            <Route exact path="/food" component={Food}/>
            <Route exact path="/activities" component={Activities}/>
            <Route exact path="/comments" component={Comments}/>
            <Route exact path="/rooms/:slug" component={SingleRoom}/>

            <Route exact path="/LoginManager" component={LoginManager}/>
            <Route exact path="/manager" component={HomeManager}/>
            <Route exact path="/assign-food-order" component={OrderManager}/>
            <Route exact path="/evaluate-training" component={Training}/>
            <Route exact path="/create-event" component={CreateEvent}/>
            <Route exact path="/assign-duty" component={AssignDuty}/>
            <Route exact path="/evaluate-leave" component={LeaveManager}/>
            <Route exact path="/confirmation" component={Confirmation}/>

            <Route component={Error}/>
        </Switch></>
    );
}

export default App;
