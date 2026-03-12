const Card = ({ viewType, children }) => {
  return (
    <>
      <div className={`card ${viewType}`}>{children}</div>
    </>
  );
};

export default Card;
