import React from "react";
import "./css/App.css";
import "antd/dist/antd.css";

import { Layout } from "antd";
import ActiveUsersSidebar from "./components/ActiveUsersSidebar/ActiveUsersSidebar";
import MessageBox from "./components/MessageBox/MessageBox";
import axios from "axios";

const { Header, Footer, Sider, Content } = Layout;

const HOST_URL = "http://localhost:8080";
const USER_JOINED_URL = "/userJoined";
const USER_LEFT_URL = "/userLeft";
const ACTIVE_USERS_URL = "/activeUsers";

class App extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      activeUsers: [],
      username: "",
    };
  }

  userJoinedAction = () => {
    axios.get(HOST_URL + USER_JOINED_URL).then((response) => {
      this.setState(() => ({
        username: response.data,
      }));
    });
  };

  userLeftAction = () => {
    axios
      .get(HOST_URL + USER_LEFT_URL, {
        params: { username: this.state.username },
      })
      .then();
  };

  handleWindowClosed = () => {
    window.addEventListener("beforeunload", (event) => {
      this.userLeftAction();
    });
  };

  fetchActiveUsers = () => {
    axios.get(HOST_URL + ACTIVE_USERS_URL).then((response) => {
      this.setState(() => ({
        activeUsers: response.data,
      }));
    });
  };

  componentDidMount() {
    this.fetchActiveUsers();
    this.userJoinedAction();
    this.handleWindowClosed();
  }

  componentDidUpdate() {
    this.fetchActiveUsers();
  }

  render() {
    return (
      <div className="App">
        <Layout>
          <Header id="header">Chat Application</Header>
          <Layout id="layout">
            <Sider id="sider">
              <ActiveUsersSidebar activeUsers={this.state.activeUsers} />
            </Sider>
            <Content id="content">
              <MessageBox user={this.state.username} />
            </Content>
          </Layout>
          <Footer id="footer">
            Developed by Jasmin Bajrić in february 2022.
          </Footer>
        </Layout>
      </div>
    );
  }
}

export default App;
