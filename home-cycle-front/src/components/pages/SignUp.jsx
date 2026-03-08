import Button from "../common/Button";
import Input from "../common/forms/Input";
import TextArea from "../common/forms/TextArea";
import { useState } from "react";
import { userService } from "../services/userService";
import { useNavigate } from "react-router";

const SignUp = () => {
  const credentialFormat = {
    email: "",
    name: "",
    password: "",
    hhNotes: "",
    password2: "",
  };
  const [newUser, setNewUser] = useState(credentialFormat);

  const handleChange = (e) => {
    const { id, value } = e.target;
    setNewUser((oldData) => ({ ...oldData, [id]: value }));
  };

  // confirms passwords match before allowing moving on
  const passwordsDontMatch =
    newUser.password !== newUser.password2 && newUser.password2.length > 0;
  const navigate = useNavigate();

  const handleClick = async (e) => {
    e.preventDefault();
    if (!passwordsDontMatch) {
      const registerDto = {
        name: newUser.name,
        email: newUser.email,
        password: newUser.password,
        householdNotes: newUser.hhNotes,
      };
      try {
        await userService.register(registerDto);
        navigate("/");
        alert("Registration successful!");
      } catch (er) {
        console.error("Error during registration:", er);
      }
    }
  };

  return (
    <>
      <div className="sign-up card add-blur">
        <h2>Sign Up</h2>
        <form onSubmit={handleClick}>
          <Input
            label="Name:"
            value={newUser.name}
            id="name"
            handleChange={handleChange}
          />
          <Input
            label="Email:"
            value={newUser.email}
            id="email"
            handleChange={handleChange}
          />
          <TextArea
            label="Any notes to keep about your house:"
            value={newUser.hhNotes}
            id="hhNotes"
            handleChange={handleChange}
          />
          <Input
            label="Password:"
            value={newUser.password}
            type="password"
            id="password"
            handleChange={handleChange}
          />
          {passwordsDontMatch && (
            <p className="error">Passwords do not match</p>
          )}
          <Input
            label="Confirm Password:"
            value={newUser.password2}
            type="password"
            id="password2"
            handleChange={handleChange}
          />
          <Button
            label="Sign Up"
            id="sign-up-button"
            type="submit"
            handleClick={handleClick}
          />
        </form>
      </div>
      <p className="about intro card" id="intro">
        Home Cycle is the solution to recurring maintenance around the home. As
        a homeowner, I have lots of small things I have to do every day, week,
        month and year it seems. I built this to help me stay organized, and
        hopefully it does the same for others.
      </p>
    </>
  );
};

export default SignUp;
