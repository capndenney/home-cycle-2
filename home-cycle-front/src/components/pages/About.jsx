import Input from "../common/forms/Input";
import TextArea from "../common/forms/TextArea";
import Button from "../common/Button";
import { useState } from "react";
import { feedbackService } from "../services/feedbackService";

const About = () => {
  const initialFeedback = { name: "", email: "", feedback: "" };
  const [inputData, setInputData] = useState(initialFeedback);
  const [isSending, setIsSending] = useState(false);

  const handleChange = (e) => {
    const { id, value } = e.target;
    setInputData((oldData) => ({ ...oldData, [id]: value }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setIsSending(true);
    try {
      await feedbackService.sendFeedback(inputData);
      alert("Thanks for the feedback!");
      setInputData(initialFeedback);
    } catch (er) {
      console.error("Error sending feedback: " + er)
      alert("Something went wrong. Please try again.");
    } finally {
      setIsSending(false);
    }
  };

  return (
    <div id="about-page">
      <h2>About Home Cycle</h2>
      <p>
        Homeownership can be daunting. There are so many things you have to do
        to keep things functional and running smoothly. With Home Cycle, all of
        your recurring maintenance tasks just got simplified. Consider us your
        central hub for keeping organized and your house in tip top shape. Did
        you know you may be missing out on efficiency in your water heater if
        you don't flush out your system? Your A/C and heater might not push as
        much air out if the filter gets dirty. <br />
        <br />
        These are just a few of the things that we can suggest you do to get
        started and then we can help you set up recurring tasks to meet best
        practices for cleaning. And if you haven't done these things before,
        that's ok, we're here to help. We can help connect you to resources on
        how to complete your task or project, or connect you with professionals
        in your are to complete it for you.
      </p>
      <br />
      <form className="feedback-form" onSubmit={handleSubmit}>
        <h3>Contact Us</h3>
        <Input
          id="name"
          value={inputData.name}
          label="Name:"
          required={true}
          handleChange={handleChange}
        />
        <Input
          id="email"
          value={inputData.email}
          label="Email:"
          required={true}
          handleChange={handleChange}
        />
        <TextArea
          id="feedback"
          label="Feedback:"
          value={inputData.feedback}
          rows="4"
          required={true}
          handleChange={handleChange}
        />
        <Button
          id="submit-feedback"
          type="submit"
          label="Submit"
          handleClick={handleSubmit}
          classes="submit button feedback"
          disabled={isSending}
        />
      </form>
    </div>
  );
};

export default About;
