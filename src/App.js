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

import Orders from './pages/Orders'
import ApplyTo from './pages/ApplyTo'
import LoginToHousekeeper from "./pages/LoginToHousekeeper"
import SecurityLogin from"./pages/SecurityLogin"
import SecurityWalks from "./pages/SecurityWalks"
import LeaveForHousekeeper from "./pages/LeaveForHousekeeper";
import TrainingForHousekeeper from "./pages/TrainingForHousekeeper"
import TrainingForSecurity from "./pages/TrainingForSecurity"
import HomeForSecurity from "./pages/HomeForSecurity"
import LeaveForSecurity from "./pages/LeaveForSecurity"
import ApplySecurity from "./pages/ApplySecurity"

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


                        <Route exact path="/applyTo/" component={ApplyTo}/>
                        <Route exact path="/applyTo/:slug" component={Orders}/>
                        <Route exact path="/HomeForHousekeeper/" component={Home}/>

                        <Route exact path="/LoginToHousekeeper/" component={LoginToHousekeeper}/>
                        <Route exact path="/LeaveForHousekeeper/" component={LeaveForHousekeeper}/>
                        <Route exact path="/HomeForSecurity/" component={HomeForSecurity}/>
                        <Route exact path="/applySecurity/" component={ApplySecurity}/>
                        <Route exact path="/LoginToSecurity/" component={SecurityLogin}/>
                        <Route exact path="/viewSecurity/" component={SecurityWalks}/>
                        <Route exact path="/SecurityApplyPage/" component={ApplySecurity}/>
                        <Route exact path="/LeaveForSecurity/" component={LeaveForSecurity}/>
                        <Route exact path="/TrainingForSecurity/" component={TrainingForSecurity}/>
                        <Route exact path="/TrainingForHousekeeper/" component={TrainingForHousekeeper}/>



                        <Route component={Error}/>
        </Switch></>
    );
}

export default App;
