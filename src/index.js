import React from "react";
import ReactDOM from "react-dom";
// import './index.css';
import App from "./App";
import { RoomProvider } from "./Context";
import { BrowserRouter } from "react-router-dom";

ReactDOM.render(
    <RoomProvider>
        <BrowserRouter>
            <App />
        </BrowserRouter>
    </RoomProvider>,
    document.getElementById("root")
);
