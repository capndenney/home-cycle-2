import Nav from "./Nav";
import logo from "../../assets/home-cycle-logo-test.PNG";

const Header = ({ logInStatus, setLogInStatus, setClicked }) => {
  return (
    <header className="header">
      <div id="branding">
        <img id="logo" src={logo} alt="Home Cycle Logo" />
        <h1 className="app-title">
          Home
          <br />
          Cycle
        </h1>
      </div>
      <Nav
        logInStatus={logInStatus}
        setLogInStatus={setLogInStatus}
        setClicked={setClicked}

      />
    </header>
  );
};

export default Header;
