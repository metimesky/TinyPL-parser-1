# Object oriented top down parser for TinyPL #

### Generates JVM byte-code for TinyPL. ###

EBNF Grammar for TinyPL:

```
program -> decls stmts end
decls -> int idlist ;
idlist - >  id [', 'idlist ]
stmts -> stmt [ stmts ]
cmpdstmt-> '{' stmts '}'
stmt -> assign | cond | loop
assign -> id = expr ;
cond -> if '(' rexp ')' cmpdstmt [ else cmpdstmt ]
loop -> while '(' rexp ')' cmpdstmt
rexp -> expr (< | > | =) expr
expr -> term [ (+ | -) expr ]
term -> factor [ (* | /) term ]
factor -> int_lit | id | '(' expr ')'
```
