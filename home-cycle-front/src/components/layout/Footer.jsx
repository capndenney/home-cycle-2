import Nav from "./Nav";

const Footer = () => {

  const year = new Date().getFullYear()

  return (
    <footer>
      <Nav />
      <h6>Thanks for visiting, &copy; {year} Home Cycle </h6>
    </footer>
  );
};

export default Footer;
