(ns concat.core
  (:require concat.backend)
  (:use [clojure.tools.cli :only [cli]])
  (:gen-class))

(def doc-str
  "Doc string")

(defn- parse-args
  [args]
  (if (every? #{"-c" "--concat" "-i" "--infile"} args)
    (cli args
         ["-c" "--concat" "File to be written to" :default ""
          :parse-fn #(clojure.java.io/as-file %)]
         ["-i" "--infile" "File to be read from" :default ""
          :parse-fn #(clojure.java.io/as-file %)]
         ["-d" "--delim" "Delimiter for file" :default ","]
         ["-v" "--verbose" :flag true]
         ["-n" "--ui" "GUI or not" :flag true]
         ["-r" "--ragged" "File columns not uniform" :flag false])))


(defn -main
  [& args]
  (let [args (parse-args args)]
    (if (nil? args)
      doc-str
      (second args))))
