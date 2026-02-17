import { Link } from "react-router";
import Button from "../common/Button";

const Nav = ({ logInStatus, setLogInStatus, setClicked }) => {

  const handleLogout = () => {
    setLogInStatus(false);
    setClicked(0);
  };

  return (
    <div className="nav-menu">
      <Link className="link new-task" to="/newtask" >
        New Task
      </Link>
      <Link className="link" to="/" >
        Home
      </Link>
      <Link className="link" to="/about">
        About
      </Link>
      {logInStatus && (
        <Button
          id="logout"
          label="Log Out"
          handleClick={handleLogout}
          classes="button"
        />
      )}
    </div>
  );
};

export default Nav;
