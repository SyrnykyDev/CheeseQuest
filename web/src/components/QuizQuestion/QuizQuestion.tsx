import React from "react";

// *** STYLES ***
import styles from "./QuizQuestion.module.scss";

// *** ASSETS ***
import deleteSVG from "../../../public/delete.svg";
import Image from "next/image";

interface IQuizQuestionProps {
  title: string;
  id: number;
  active?: boolean;
  editable?: boolean;
  onQuestionDelete?: (id: number) => void;
  filled?: boolean;
  position?: number;
  draggable?: boolean;
  dirty?: boolean;
  description?: string;
}
const QuizQuestion = ({
  title,
  active,
  editable,
  onQuestionDelete,
  filled,
  id,
  position,
  description,
  dirty,
  draggable = true,
}: IQuizQuestionProps) => {
  return (
    <div
      draggable
      className={`${styles.quizQuestion} ${active ? styles.quizQuestion__active : ""} 
      ${editable && !active && !filled && dirty ? styles.quizQuestion__red : active && dirty ? "" : styles.quizQuestion__green}
      `}
    >
      {/*{id}*/}
      <div style={{ position: "absolute", left: "10px" }}>{position}</div>
      {editable && (
        <div
          style={{ position: "absolute", right: "10px" }}
          onClick={() => onQuestionDelete && onQuestionDelete(id)}
        >
          <Image src={deleteSVG} alt={"Delete"} width={30} />
        </div>
      )}
      <div className={styles.quizQuestion_title}>{title}</div>
      <div className={styles.quizQuestion_description}>{description}</div>
      {/*<span className={styles. }></span>*/}
    </div>
  );
};

export default QuizQuestion;
