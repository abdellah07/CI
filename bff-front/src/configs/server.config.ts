import {HttpHeaders} from "@angular/common/http";

export const headerOptions = {
  'Content-Type': 'application/json',
  'Accept': 'application/json',
  'Access-Control-Allow-Credentials': 'true',
  'Access-Control-Allow-Origin': '*',
  'Access-Control-Allow-Methods': 'GET, POST, PATCH, DELETE, PUT, OPTIONS',
  'Access-Control-Allow-Headers': 'Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With',
}


export const bffURL = 'http://localhost:8080';
export const orderUrl = bffURL + '/order';
export const menuUrl = bffURL + '/menu';
export const orderinfoUrl = bffURL + '/preparations';
export const prepareUrl = bffURL + '/preparations/serve';
