interface IRandomNumberProps {
  min?: number;
  max?: number;
}
const RandomNumber = (props?: IRandomNumberProps) => {
  const max = props?.max || 100000;
  const min = props?.min || 0;
  const rand = min + Math.random() * (max - min);
  return rand;
};

export default RandomNumber;
