<?php
class FooController extends WikiaController {
    public function executeFooBar( $params) {} // NOK

    public function executeSomethingElse() {} // OK

    public function robota() {} // OK
}

class FooService extends WikiaService {
    public function executeFooBar( $params) {} // NOK

    public function executeSomethingElse() {} // OK

    public function robota() {} // OK
}

class Foo {
    public function executeIsOkHere( $params ) {} // OK
}