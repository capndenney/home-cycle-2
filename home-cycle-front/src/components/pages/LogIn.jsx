import Button from "../common/Button";
import Input from "../common/forms/Input";
import { useState } from "react";
import sampleUsers from "../../sampleData/sampleUsers";

const LogIn = ({ setLogInStatus, clicked, setClicked }) => {
  const credentialFormat = { username: "", password: "" };
  const [creds, setCreds] = useState(credentialFormat);

  function validateLogin(inputUsername, inputPassword) {
    return sampleUsers.some((credentials) => {
      return (
        credentials.username === inputUsername &&
        credentials.password === inputPassword
      );
    });
  }

  const handleChange = (e) => {
    const { id, value } = e.target;
    setCreds((oldData) => ({ ...oldData, [id]: value }));
  };

  const handleClick = (e) => {
    e.preventDefault();
    if (validateLogin(creds.username, creds.password)) {
      setLogInStatus(true);
    } else {
      setClicked((clicked) => clicked + 1);
    }
  };

  return (
    <>
      <div className="log-in card add-blur">
        <h2>Log In</h2>
        {clicked > 0 && <h4 className="invalid">Invalid Credentials!</h4>}

        <Input
          label="Username:"
          value={creds.username}
          id="username"
          handleChange={handleChange}
        />
        <Input
          label="Password:"
          value={creds.password}
          type="password"
          id="password"
          handleChange={handleChange}
        />
        <Button label="Log In" id="log-in-button" handleClick={handleClick} />
      </div>
      <p className="about intro card" id="intro">Home Cycle is the solution to recurring maintenance around the home. As a homeowner, I have lots of small things I have to do every day, week, month and year it seems.  I built this to help me stay organized, and hopefully it does the same for others.</p>
    </>
  );
};

export default LogIn;
